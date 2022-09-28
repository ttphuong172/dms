import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CategoryService} from "../../../service/category.service";
import {DeviceService} from "../../../service/device.service";
import {TypeService} from "../../../service/type.service";
import {CampusService} from "../../../service/campus.service";
import {FloorService} from "../../../service/floor.service";
import {RoomService} from "../../../service/room.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-room-device-add',
  templateUrl: './room-device-add.component.html',
  styleUrls: ['./room-device-add.component.css']
})
export class RoomDeviceAddComponent implements OnInit {
  deviceForm: FormGroup | any;
  categoryList: any;
  typeList: any;
  campusList: any;
  room: any;
  roomId:any


  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private deviceService: DeviceService,
    private typeService: TypeService,
    private campusService: CampusService,
    private roomService:RoomService,
    private snackBar: MatSnackBar,
    private router:Router
  ) {
  }

  ngOnInit(): void {
    this.roomId = String(this.activatedRoute.snapshot.paramMap.get('roomId'));
    this.deviceForm = new FormGroup({
      id: new FormControl(''),
      category: new FormControl(''),
      useDate: new FormControl(''),
      brand: new FormControl(''),
      model: new FormControl(''),
      serial: new FormControl(''),
      note: new FormControl(''),
      configurationList: this.formBuilder.array([]),
      campus: new FormControl(''),
      floor: new FormControl(''),
      room: new FormControl(''),
    })

    this.categoryService.findAll().subscribe(
      (data) => {
        this.categoryList = data
      },
      () => {
      },
      () => {
        this.typeService.findAll().subscribe(
          (data) => {
            this.typeList = data;
          },
          () => {
          },
          () => {
            this.campusService.findAll().subscribe(
              (data) => {
                this.campusList = data;
              },()=>{},()=>{
                this.roomService.findById(this.roomId).subscribe(
                  (data)=>{
                    this.room=data;
                    this.deviceForm.controls['campus'].setValue(this.room.floor.campus);
                    this.deviceForm.controls['floor'].setValue(this.room.floor);
                    this.deviceForm.controls['room'].setValue(this.room);
                  }
                )
              }
            )
          }
        )
      }
    )
  }

  cancel() {

  }

  save() {
    this.deviceService.save(this.deviceForm.value).subscribe(
      (data) => {
        if (data == null) {
          this.snackBar.open("Device ID  is exist!", 'Undo', {duration: 1500});
        } else {
          this.router.navigateByUrl('rooms/detail/' + this.roomId)
          this.snackBar.open("Add successful", 'Undo', {duration: 1500});
        }

      }
    )
  }

  setConfiguraton(type: any, detailType: any,setDate:any): FormGroup {
    return this.formBuilder.group({
      type: type || '',
      detail: detailType || '',
      setDate: setDate || ''
    })
  }

  get configuration(): FormArray {
    return this.deviceForm.get('configurationList') as FormArray;
  }

  addConfig() {
    this.configuration.push(this.setConfiguraton(null, null,null));
  }

  removeConfig(i: number) {
    this.configuration.removeAt(i);
  }
}
