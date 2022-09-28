import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DeviceService} from "../../../service/device.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {DeviceDeleteComponent} from "../device-delete/device-delete.component";

@Component({
  selector: 'app-device-detail',
  templateUrl: './device-detail.component.html',
  styleUrls: ['./device-detail.component.css']
})
export class DeviceDetailComponent implements OnInit {
  device: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private deviceService: DeviceService,
    private snackBar: MatSnackBar,
    private matDialog:MatDialog
  ) {
  }

  ngOnInit(): void {
    const id = String(this.activatedRoute.snapshot.paramMap.get('id'));
    this.deviceService.findById(id).subscribe(
      (data) => {
        if (data == null) {
          this.snackBar.open("Device not found", 'Undo', {duration: 1500});
        } else {
          this.device=data;
          console.log(this.device)
        }
      }
    )
  }

  openDialogDelete(device: any) {
    const dialogRefDelete = this.matDialog.open(DeviceDeleteComponent, {
      width: '600px',
      data: device,
      disableClose: true
    })
    dialogRefDelete.afterClosed().subscribe(
      () => {
      },
      () => {
      },
      () => {
        // this.ngOnInit();
      }
    )
  }
}
