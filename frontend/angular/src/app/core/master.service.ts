import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MasterService {
  public responseType : string;
  public responseValue : {};
  constructor() { }

  getResponseType(data: {}){
    this.responseType = data["result"]["inventoryResponse"]["responseType"];
    return this.responseType;
  } 

  getResponseValue(data: {}){
    this.responseValue = data["result"]["inventoryResponse"]["responseObj"];
    return this.responseValue;
  }
}
