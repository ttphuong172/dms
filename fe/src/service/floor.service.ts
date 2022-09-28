import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FloorService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient:HttpClient
  ) { }
  findAll(){
    return this.httpClient.get(this.apiURL+'/api/floors');
  }
  findById(id:any){
    return this.httpClient.get(this.apiURL+'/api/floors/' + id);
  }

  findDTOById(id:any){
    return this.httpClient.get(this.apiURL+'/api/floors/dto/' + id);
  }

  findFloorByCampus_Name(campusName:any){
    return this.httpClient.get(this.apiURL+ '/api/floors/campus/name/' + campusName);
  }
  findRoomDTOByFloorId(floorId:any){
    return this.httpClient.get(this.apiURL+'/api/floors/rooms/'+ floorId);
  }
  findDeviceByFloorId(floorId: any) {
    return this.httpClient.get(this.apiURL+'/api/floors/devices/' + floorId);
  }
  save(floor:any){
    return this.httpClient.post(this.apiURL+'/api/floors',floor);
  }
}
