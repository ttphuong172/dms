import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DeviceService} from "../../../service/device.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-device-mantain',
  templateUrl: './device-mantain.component.html',
  styleUrls: ['./device-mantain.component.css']
})
export class DeviceMantainComponent implements OnInit {
  deviceId:any;
  device:any;
  date=new Date();

  mantainRequestDTOForm=new FormGroup({
    mantainDate:new FormControl((new Date()).toISOString().substring(0, 10)),
    contentMantain:new FormControl('',Validators.required),
    personName:new FormControl('',Validators.required)
  })

  constructor(
    private activatedRoute: ActivatedRoute,
    private deviceService:DeviceService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.deviceId = String(this.activatedRoute.snapshot.paramMap.get('id'));
    this.deviceService.findById(this.deviceId).subscribe(
      (data)=>{
        this.device=data;
      }
    )
  }

  mantain() {
    this.deviceService.mantain(this.device.id,this.mantainRequestDTOForm.value).subscribe(
      ()=>{},
      ()=>{},
      ()=>{
        this.router.navigateByUrl("/devices/detail/" + this.deviceId)
      }
    )
  }
}
