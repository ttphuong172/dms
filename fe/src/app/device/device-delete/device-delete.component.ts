import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DeviceService} from "../../../service/device.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-device-delete',
  templateUrl: './device-delete.component.html',
  styleUrls: ['./device-delete.component.css']
})
export class DeviceDeleteComponent implements OnInit {
  device:any;

  constructor(
    private deviceService :DeviceService,
    private router:Router,
    public dialogRefDelete: MatDialogRef<DeviceDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
  ) { }

  ngOnInit(): void {
    this.device=this.data;
    // console.log(this.device)
  }

  closeDialogDelete() {
    this.dialogRefDelete.close()
  }

  delete(data: any) {
    this.deviceService.delete(data).subscribe(
      ()=>{},
      ()=>{},
      ()=>{
        this.router.navigateByUrl('/rooms/detail/' + this.device.room.id);
        this.dialogRefDelete.close();
      }
    )
  }
}
