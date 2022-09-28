import {Component, OnInit} from '@angular/core';
import {FloorService} from "../../../service/floor.service";
import {ActivatedRoute} from "@angular/router";
import {CampusService} from "../../../service/campus.service";

@Component({
  selector: 'app-campus-detail',
  templateUrl: './campus-detail.component.html',
  styleUrls: ['./campus-detail.component.css']
})
export class CampusDetailComponent implements OnInit {
  floorDTOList: any;
  campus: any;

  constructor(
    private campusService: CampusService,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.campusService.findFloorDTOByCampusId(id).subscribe(
      (data) => {
        this.floorDTOList = data;
        // console.log(this.floorDTOList)
      }, () => {
      }, () => {
        this.campusService.findDTOById(id).subscribe(
          (data) => {
            this.campus = data;
            // console.log(this.campus)
          }
        )
      }
    )
  }

}
