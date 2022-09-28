import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FloorService} from "../../../service/floor.service";

@Component({
  selector: 'app-floor-device-list',
  templateUrl: './floor-device-list.component.html',
  styleUrls: ['./floor-device-list.component.css']
})
export class FloorDeviceListComponent implements OnInit {
  floorId:any;
  deviceList:any;
  constructor(
    private activatedRoute:ActivatedRoute,
    private floorService:FloorService
  ) { }

  ngOnInit(): void {
    this.floorId = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.floorService.findDeviceByFloorId(this.floorId).subscribe(
      (data)=>{
        this.deviceList=data;
        console.log(this.deviceList);
      }
    )
  }

}
