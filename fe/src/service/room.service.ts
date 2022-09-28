import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient: HttpClient
  ) {
  }

  findById(id:any){
    return this.httpClient.get(this.apiURL+'/api/rooms/'+ id);
  }
  findDTOById(id:any){
    return this.httpClient.get(this.apiURL+'/api/rooms/dto/'+ id);
  }

  findRoomByFloor_Name(floorName: any) {
    return this.httpClient.get(this.apiURL+'/api/rooms/floor/name/' + floorName);
  }

  findDeviceByRoomId(roomId: any) {
    return this.httpClient.get(this.apiURL+'/api/rooms/devices/' + roomId);
  }
  save(room:any){
    return this.httpClient.post(this.apiURL+'/api/rooms',room)
  }
}
