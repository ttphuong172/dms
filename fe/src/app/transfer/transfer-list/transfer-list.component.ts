import { Component, OnInit } from '@angular/core';
import {TransferService} from "../../../service/transfer.service";

@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.css']
})
export class TransferListComponent implements OnInit {
  transferList:any;
  p: any;
  constructor(
    private transferService:TransferService
  ) { }

  ngOnInit(): void {
    this.transferService.findAll().subscribe(
      (data)=>{
        this.transferList=data;
        // console.log(this.transferList);
        for (let i=0;i< this.transferList.length;i++){

        }
      }
    )
  }
}
