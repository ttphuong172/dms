import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {FloorService} from "../../../service/floor.service";
import {RoomService} from "../../../service/room.service";

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.css']
})
export class RoomAddComponent implements OnInit {
  floorId: any;
  floor: any;
  roomForm = new FormGroup({
    name: new FormControl(''),
    floor: new FormControl('')
  })

  constructor(
    private activatedRoute: ActivatedRoute,
    private floorService: FloorService,
    private roomService: RoomService,
    private router:Router
  ) {
  }


  ngOnInit(): void {
    this.floorId = String(this.activatedRoute.snapshot.paramMap.get('floorId'));
    // console.log(this.floorId)
    this.floorService.findById(this.floorId).subscribe(
      (data) => {
        this.floor = data;
        this.roomForm.controls['floor'].setValue(this.floor);
      }
    )

  }

  save() {
    this.roomService.save(this.roomForm.value).subscribe(
      ()=>{},
      ()=>{},
      ()=>{
        this.router.navigateByUrl("/floors/detail/" + this.floorId)
      }
    )
  }
}
