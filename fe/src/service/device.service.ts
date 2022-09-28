import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient:HttpClient
  ) { }
  findAll(){
    return this.httpClient.get(this.apiURL+ '/api/devices');
  }
  save(device:any){
    return this.httpClient.post(this.apiURL+ '/api/devices',device);
  }
  findById(id:any){
    return this.httpClient.get(this.apiURL+ '/api/devices/' + id);
  }
  delete(device:any){
    return this.httpClient.delete(this.apiURL+ '/api/devices/' + device.id)
  }
  update(device:any){
    return this.httpClient.put(this.apiURL+ '/api/devices/'+ device.id,device)
  }

  mantain(id:any, mantainRequestDTO:any){
    return this.httpClient.put(this.apiURL+'/api/devices/mantain/'+ id,mantainRequestDTO)
  }
  searchDevice(id:any,idCategory:any,brand:any,idCampus:any,idFloor:any,idRoom:any){
    return this.httpClient.get(this.apiURL+ '/api/devices/search?id='+ id + '&idCategory='+ idCategory + '&brand=' + brand + '&idCampus='+ idCampus + '&idFloor='+ idFloor + '&idRoom='+ idRoom);
  }
  getStatisticDevice(){
    return this.httpClient.get(this.apiURL+ '/api/devices/statistic');
  }

}
