import { Component, OnInit } from '@angular/core';
import {CampusService} from "../../../service/campus.service";

@Component({
  selector: 'app-campus-list',
  templateUrl: './campus-list.component.html',
  styleUrls: ['./campus-list.component.css']
})
export class CampusListComponent implements OnInit {
  campusDTOList: any;
  org:any;

  constructor(
    private campusService:CampusService
  ) { }

  ngOnInit(): void {
    this.campusService.findAllDTO().subscribe(
      (data)=>{
        this.campusDTOList=data
        // console.log(this.campusDTOList)
        this.campusService.getStatistic().subscribe(
          (data)=>{
            // console.log(data);
            this.org=data
          }
        )
      }
    )
  }

}
