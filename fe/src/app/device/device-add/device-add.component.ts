import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CategoryService} from "../../../service/category.service";
import {DeviceService} from "../../../service/device.service";
import {TypeService} from "../../../service/type.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {CampusService} from "../../../service/campus.service";
import {FloorService} from "../../../service/floor.service";
import {RoomService} from "../../../service/room.service";

@Component({
  selector: 'app-device-add',
  templateUrl: './device-add.component.html',
  styleUrls: ['./device-add.component.css']
})
export class DeviceAddComponent implements OnInit {
  deviceForm: FormGroup | any;
  categoryList: any;
  typeList: any;
  campusList: any;
  floorList: any;
  nameCampusSelected: any;
  nameFloorSelected: any;
  roomList: any;



  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private deviceService: DeviceService,
    private typeService: TypeService,
    private campusService: CampusService,
    private floorService: FloorService,
    private roomService: RoomService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.deviceForm = new FormGroup({
      id: new FormControl(''),
      category: new FormControl(''),
      useDate: new FormControl(''),
      brand: new FormControl(''),
      model: new FormControl(''),
      serial: new FormControl(''),
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
              }
            )
          }
        )
      }
    )

  }

  setConfiguraton(type: any, detailType: any,setDate:any): FormGroup {
    return this.formBuilder.group({
      type: type || '',
      detail: detailType || '',
      setDate: detailType || ''
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

  save() {
    this.deviceService.save(this.deviceForm.value).subscribe(
      (data) => {
        if (data == null) {
          this.snackBar.open("Device ID  is exist!", 'Undo', {duration: 1500});
        } else {
          this.router.navigateByUrl('devices')
          this.snackBar.open("Add successful", 'Undo', {duration: 1500});
        }

      }
    )
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
    console.log(this.nameFloorSelected);
    this.roomService.findRoomByFloor_Name(this.nameFloorSelected).subscribe(
      (data) => {
        this.roomList = data;
      }
    )
  }

  cancel() {
    this.router.navigateByUrl("/devices")
  }
}
