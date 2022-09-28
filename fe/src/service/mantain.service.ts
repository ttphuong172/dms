import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MantainService {
  private apiURL=environment.apiURL;
  constructor(
    private httpClient:HttpClient
  ) { }
  findAll(){
    return this.httpClient.get(this.apiURL+'/api/mantains');
  }
}
