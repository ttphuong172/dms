import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient:HttpClient
  ) { }
  findAll(){
    return this.httpClient.get(this.apiURL+'/api/transfers')
  }
  save(transfer:any){
    return this.httpClient.post(this.apiURL+'/api/transfers', transfer)
  }
  findById(id:any){
    return this.httpClient.get(this.apiURL+ '/api/transfers/'+id)
  }
}
