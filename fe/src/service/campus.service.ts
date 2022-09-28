import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CampusService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient:HttpClient
  ) { }

  findById(id:any){
    return this.httpClient.get(this.apiURL+'/api/campus/'+id);
  }

  findDTOById(id:any){
    return this.httpClient.get(this.apiURL+'/api/campus/dto/'+id);
  }

  findAll(){
    return this.httpClient.get( this.apiURL+'/api/campus');
  }
  findAllDTO(){
    return this.httpClient.get(this.apiURL+'/api/campus/dto');
  }
  findFloorDTOByCampusId(campusId:any){
    return this.httpClient.get(this.apiURL+'/api/campus/floors/' + campusId);
  }
  getStatistic(){
    return this.httpClient.get(this.apiURL + '/api/campus/statistic');
  }
}
