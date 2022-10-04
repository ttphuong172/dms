import {Component, OnInit} from '@angular/core';
import {FloorService} from "../../../service/floor.service";
import {CampusService} from "../../../service/campus.service";
import {RoomService} from "../../../service/room.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DeviceService} from "../../../service/device.service";
import {Device} from "../../model/Device";
import {TransferService} from "../../../service/transfer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {finalize} from "rxjs/operators";
import {WebcamImage} from "ngx-webcam";
import {Observable, Subject} from "rxjs";

@Component({
  selector: 'app-transfer-add',
  templateUrl: './transfer-add.component.html',
  styleUrls: ['./transfer-add.component.css']
})
export class TransferAddComponent implements OnInit {
  deviceList: Device[] = [];
  device: any;
  nameCampusSelected: any;
  nameFloorSelected: any;
  campusList: any;
  floorList: any;
  roomList: any;
  deviceId: any;
  date = new Date();
  selectedFile: File | any;
  url: string | any;
  id: any;

  private trigger: Subject<any> = new Subject();

  public webcamImage!: WebcamImage;
  private nextWebcam: Subject<any> = new Subject();

  captureImage  = '';

  transferForm = new FormGroup({
    transferDate: new FormControl((new Date()).toISOString().substring(0, 10)),
    room: new FormControl('', Validators.required),
    personInCharge: new FormControl('', Validators.required),
    evidence: new FormControl(''),
    deviceList: new FormControl('', Validators.required),
  })

  constructor(
    private campusService: CampusService,
    private floorService: FloorService,
    private roomService: RoomService,
    private deviceService: DeviceService,
    private transferService: TransferService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar,
    private angularFireStorage: AngularFireStorage,
  ) {
  }

  ngOnInit(): void {
    let id = String(this.activatedRoute.snapshot.paramMap.get('id'));
    // console.log(id)
    // console.log(typeof id);

    this.campusService.findAll().subscribe(
      (data) => {
        this.campusList = data;
        if (id !== "null") {
          this.deviceService.findById(id).subscribe(
            (data)=>{
              this.device=data;
              this.deviceList.unshift(this.device);
              this.transferForm.controls['deviceList'].setValue(this.deviceList);
            }
          )
        }
      }
    )
  }

  /*------------------------------------------
 --------------------------------------------
 triggerSnapshot()
 --------------------------------------------
 --------------------------------------------*/
  public triggerSnapshot(): void {
    this.trigger.next();
  }

  /*------------------------------------------
  --------------------------------------------
  handleImage()
  --------------------------------------------
  --------------------------------------------*/
  public handleImage(webcamImage: WebcamImage): void {
    this.webcamImage = webcamImage;
    this.captureImage = webcamImage!.imageAsDataUrl;

    // console.info('received webcam image', this.captureImage);
    // console.log(this.captureImage)
    this.transferForm.controls['evidence'].setValue(this.captureImage);
  }

  /*------------------------------------------
  --------------------------------------------
  triggerObservable()
  --------------------------------------------
  --------------------------------------------*/
  public get triggerObservable(): Observable<any> {

    return this.trigger.asObservable();
  }

  /*------------------------------------------
  --------------------------------------------
  nextWebcamObservable()
  --------------------------------------------
  --------------------------------------------*/
  public get nextWebcamObservable(): Observable<any> {

    return this.nextWebcam.asObservable();
  }

  loadFloor(event: any) {
    this.nameCampusSelected = event.target.selectedOptions[0].innerHTML;
    this.floorService.findFloorByCampus_Name(this.nameCampusSelected).subscribe(
      (data) => {
        this.floorList = data
      }
    )
  }

  loadRoom(event: any) {
    this.nameFloorSelected = event.target.selectedOptions[0].innerHTML;
    this.roomService.findRoomByFloor_Name(this.nameFloorSelected).subscribe(
      (data) => {
        this.roomList = data;
      }
    )
  }

  searchDevice($event: any) {
    let id = $event.target.value;
    if (id == '') {
      this.snackBar.open("Enter a device id", 'Undo', {duration: 1500});
    } else {
      this.deviceService.findById(id).subscribe(
        (data) => {
          // console.log(data)
          if (data) {
            this.device = data;

            //Test book inserted into bookList
            let isExist = false;
            for (let i = 0; i < this.deviceList.length; i++) {
              if (this.device.id == this.deviceList[i].id) {
                isExist = true;
                break;
              }
            }


            if (!isExist) {
              // @ts-ignore
              this.deviceList.unshift(this.device);
              this.transferForm.controls['deviceList'].setValue(this.deviceList);
            } else {
              this.snackBar.open("Device is already on the list!", 'Undo', {duration: 1500});
            }


          } else {
            this.snackBar.open("Device is not exist!", 'Undo', {duration: 1500});
          }

        }
      )

      this.deviceId = ''
    }
  }

  transfer() {
    this.transferService.save(this.transferForm.value).subscribe(
      (data) => {
        this.router.navigateByUrl('/transfers')
      }
    )
  }

  selectFile(event: any) {
    const path = this.transferForm.controls['transferDate'].value + "-" + Math.floor(Math.random() * 10);
    ;
    this.selectedFile = event.target.files[0]
    // console.log(this.selectedFile)
    this.angularFireStorage.upload(path, this.selectedFile)
      .snapshotChanges().pipe(
      finalize(() => {
        this.angularFireStorage.ref(path).getDownloadURL().subscribe(
          (data) => {
            this.url = data;
            // console.log(this.url)
            this.transferForm.controls['evidence'].setValue(this.url)
          }
        )
      })
    ).subscribe();
  }

  deteleDevice(device: any) {
    for (let i = 0; i < this.deviceList.length; i++) {
      if (device.id == this.deviceList[i].id) {
        this.deviceList.splice(i, 1)
      }
    }
  }
}
