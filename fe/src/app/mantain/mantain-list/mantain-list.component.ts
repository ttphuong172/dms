import { Component, OnInit } from '@angular/core';
import {MantainService} from "../../../service/mantain.service";

@Component({
  selector: 'app-mantain-list',
  templateUrl: './mantain-list.component.html',
  styleUrls: ['./mantain-list.component.css']
})
export class MantainListComponent implements OnInit {
  mantainList:any;
  p: any;
  constructor(
    private mantainService:MantainService
  ) { }

  ngOnInit(): void {
    this.mantainService.findAll().subscribe(
      (data)=>{
        this.mantainList=data;
        // console.log(this.mantainList);
      }
    )
  }

}
