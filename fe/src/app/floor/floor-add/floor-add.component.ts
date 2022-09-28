import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {CampusService} from "../../../service/campus.service";
import {FloorService} from "../../../service/floor.service";

@Component({
  selector: 'app-floor-add',
  templateUrl: './floor-add.component.html',
  styleUrls: ['./floor-add.component.css']
})
export class FloorAddComponent implements OnInit {
  campusId:any;
  campus: any;
  floorForm = new FormGroup({
    name: new FormControl(''),
    campus: new FormControl('')
  })

  constructor(
    private activatedRoute:ActivatedRoute,
    private campusService:CampusService,
    private floorService:FloorService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.campusId = Number(this.activatedRoute.snapshot.paramMap.get('campusId'));
    // console.log(this.campusId)
    this.campusService.findById(this.campusId).subscribe(
      (data)=>{
        this.campus=data;
        this.floorForm.controls['campus'].setValue(this.campus);
      }
    )
  }

  save() {
    this.floorService.save(this.floorForm.value).subscribe(
      ()=>{},
      ()=>{},
      ()=>{
        this.router.navigateByUrl("/campus/detail/" + this.campusId)
      }
    )
  }
}
