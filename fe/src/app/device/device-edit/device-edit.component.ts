import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {CategoryService} from "../../../service/category.service";
import {DeviceService} from "../../../service/device.service";
import {TypeService} from "../../../service/type.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CampusService} from "../../../service/campus.service";
import {FloorService} from "../../../service/floor.service";
import {RoomService} from "../../../service/room.service";

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrls: ['./device-edit.component.css']
})
export class DeviceEditComponent implements OnInit {
  deviceForm: FormGroup | any;
  categoryList: any;
  typeList: any;
  device: any;
  campusList: any;
  floorList: any;
  roomList: any;
  nameCampusSelected: any;
  nameFloorSelected: any;
  statusList=['Good', 'Fault']

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private deviceService: DeviceService,
    private typeService: TypeService,
    private campusService: CampusService,
    private floorService: FloorService,
    private roomService:RoomService,
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
      note: new FormControl(''),
      status: new FormControl(''),
      configurationList: this.formBuilder.array([]),
      campus: new FormControl(''),
      floor: new FormControl(''),
      room: new FormControl(''),
    })

    const id = String(this.activatedRoute.snapshot.paramMap.get('id'));
    this.deviceService.findById(id).subscribe(
      (data) => {
        this.device = data
        this.deviceForm.controls['id'].setValue(this.device.id)
        this.deviceForm.controls['category'].setValue(this.device.category)
        this.deviceForm.controls['useDate'].setValue(this.device.useDate)
        this.deviceForm.controls['brand'].setValue(this.device.brand)
        this.deviceForm.controls['model'].setValue(this.device.model)
        this.deviceForm.controls['serial'].setValue(this.device.serial)
        this.deviceForm.controls['note'].setValue(this.device.note)
        this.deviceForm.controls['status'].setValue(this.device.status)
        for (let i = 0; i < this.device.configurationList.length; i++) {
          this.deviceForm.controls['configurationList'].push(this.setConfiguraton(this.device.configurationList[i].type, this.device.configurationList[i].detail,this.device.configurationList[i].setDate))
        }
        this.deviceForm.controls['campus'].setValue(this.device.campus)
        this.deviceForm.controls['floor'].setValue(this.device.floor)
        this.deviceForm.controls['room'].setValue(this.device.room)
      }
    )


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
          }, () => {
          },
          () => {
            this.campusService.findAll().subscribe(
              (data) => {
                this.campusList = data;
              },
              () => {
              },
              () => {
                this.floorService.findFloorByCampus_Name(this.deviceForm.get('campus')?.value.name).subscribe(
                  (data) => {
                    this.floorList = data;
                  }, () => {
                  }, () => {
                    // console.log(this.deviceForm.get('floor')?.value.name);
                    this.roomService.findRoomByFloor_Name(this.deviceForm.get('floor')?.value.name).subscribe(
                      (data)=>{
                        this.roomList=data
                      }
                    )
                  }
                )
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

  compareByID(obj1: any, obj2: any) {
    return obj1 && obj2 && obj1.id == obj2.id
  }

  update() {
    this.deviceService.update(this.deviceForm.value).subscribe(
      () => {
      },
      () => {
      },
      () => {
        this.router.navigateByUrl("/devices/detail/" + this.deviceForm.controls['id'].value)
      }
    )
  }

  loadFloor($event: any) {
    this.nameCampusSelected = $event.target.selectedOptions[0].innerHTML;
    console.log(this.nameCampusSelected)
    this.floorService.findFloorByCampus_Name(this.nameCampusSelected).subscribe(
      (data) => {
        this.floorList = data
        this.deviceForm.controls['floor'].setValue('');
        this.deviceForm.controls['room'].setValue('');
        this.roomList=null;
      }
    )
  }

  loadRoom($event: any) {
    this.nameFloorSelected = $event.target.selectedOptions[0].innerHTML;
    console.log(this.nameFloorSelected);
    this.roomService.findRoomByFloor_Name(this.nameFloorSelected).subscribe(
      (data)=>{
        this.roomList=data;
      }
    )
  }

  cancel() {
    this.router.navigateByUrl("/devices/detail/" + this.deviceForm.controls['id'].value)
  }
}
