import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import 'rxjs/Rx';
import { CONFIG } from '../config';
import { Http,HttpModule, Response, RequestOptions, Headers, Request, RequestMethod } from '@angular/http';
import { HttpClient,HttpErrorResponse } from '@angular/common/http';
import { Router } from "@angular/router";
import { LocalStorage } from './local_storage.service';
import { map, catchError} from 'rxjs/operators';

@Injectable()

export class HyperService {
    private BASE_URL: string = '';
    private local_jwt: any = {}
    private header_token: string;
    private user_role: string;

    constructor(public http: Http, public router: Router, private httpClient: HttpClient ) {
        this.http = http;
    }

    //GET METHOD//
    get(endPoint: string) {
        let headers: Headers = new Headers();
        let requestoptions: RequestOptions;
        headers.append("Content-Type", 'application/json');
        headers.append("tokenkey", LocalStorage.getValue("token"));
        requestoptions = new RequestOptions({
            method: RequestMethod.Get,
            url: CONFIG._url + endPoint,
            headers: headers
        })
        return this.http.request(new Request(requestoptions))
            .toPromise()
            .then((res) => {
                return {
                    status: res.status,
                    result: res.json()
                }
            }, (err) => {
                console.log(err)
                if (err.status == 401) {
                    // let msg = err.json();
                    // alert(msg.result.message)
                    return {
                        status: err.status,
                        result: err.json()
                    }
                } else {
                    return {
                        status: err.status,
                        result: err.json()
                    };
                }

            })
    }


     //GET METHOD//
     getwithoutjson(endPoint: string) {
        let headers: Headers = new Headers();
        let requestoptions: RequestOptions;
        // headers.append("Content-Type", 'application/json');
        headers.append("tokenkey", LocalStorage.getValue("token"));
        requestoptions = new RequestOptions({
            method: RequestMethod.Get,
            url: CONFIG._url + endPoint,
            headers: headers
        })
        return this.http.request(new Request(requestoptions))
            .toPromise()
            .then((res) => {
                return {
                    status: res.status,
                    result: res
                }
            }, (err) => {
                console.log(err)
                if (err.status == 401) {
                    // let msg = err.json();
                    // alert(msg.result.message)
                    return {
                        status: err.status,
                        result: err
                    }
                } else {
                    return {
                        status: err.status,
                        result: err
                    };
                }

            })
    }

    // download file using blob
    getFile(endPoint: string) {
        return this.httpClient.get(CONFIG._url+endPoint,{
            responseType: 'blob'
          })
    .pipe(
      map((res: any) => {
        return new Blob([res], { type: 'application/pdf' })
      })
    );
    }

 
    //POST METHOD//
    post(endPoint: string, data: any, isJSON: boolean = true): any {
        let headers: Headers = new Headers();
        let requestoptions: RequestOptions;
        let theBody: any;
        if (isJSON) {
            theBody = JSON.stringify(data);
        } else {
            theBody = data
        }
        headers.append("Content-Type", 'application/json');
        headers.append("tokenkey", LocalStorage.getValue("token"));
        
        requestoptions = new RequestOptions({
            method: RequestMethod.Post,
            url: CONFIG._url + endPoint,
            headers: headers,
            body: theBody
        })

       // console.log(requestoptions)
        return this.http.request(new Request(requestoptions))
            .toPromise()
            .then((res) => {
                return {
                    status: res.status,
                    result: res.json()
                }
            }, (err) => {
                if (err.status == 401) {
                    return {
                        status: err.status,
                        result: err.json()
                    };
                } else {
                    return {
                        status: err.status,
                        result: err.json()
                    };
                }

            })
    }

 //PUT METHOD//
 put(endPoint: string, data: any, isJSON: boolean = true): any {
    let headers: Headers = new Headers();
    let requestoptions: RequestOptions;
    let theBody: any;
    if (isJSON) {
        theBody = JSON.stringify(data);
    } else {
        theBody = data
    }
    headers.append("Content-Type", 'application/json');
    headers.append("Authorization", 'Bearer ' + LocalStorage.getValue('token'));
    headers.append('Access-Control-Allow-Origin', '*');
    requestoptions = new RequestOptions({
        method: RequestMethod.Put,
        url: CONFIG._url + endPoint,
        headers: headers,
        body: theBody
    })
    return this.http.request(new Request(requestoptions))
        .toPromise()
        .then((res) => {
            return {
                status: res.status,
                result: res.json()
            }
        }, (err) => {
            if (err.status == 401) {
                return {
                    status: err.status,
                    result: err.json()
                };
            } else {
                return {
                    status: err.status,
                    result: err.json()
                };
            }

        })
}


//POST FOR FILE UPLOAD METHOD//
post_fileUpload(endPoint: string, data: any, isJSON: boolean = true): any {
    let headers: Headers = new Headers();
    let requestoptions: RequestOptions;
    let theBody: any;
    if (isJSON) {
        theBody = JSON.stringify(data);
    } else {
        theBody = data
    }
    headers.append("Cache", 'false');
    headers.append("Process-Data", 'false');
    headers.append("tokenkey", LocalStorage.getValue("token"));
    
    requestoptions = new RequestOptions({
        method: RequestMethod.Post,
        url: CONFIG._url + endPoint,
        headers: headers,
        body: theBody
    })

   // console.log(requestoptions)
    return this.http.request(new Request(requestoptions))
        .toPromise()
        .then((res) => {
            return {
                status: res.status,
                result: res.json()
            }
        }, (err) => {
            if (err.status == 401) {
                return {
                    status: err.status,
                    result: err.json()
                };
            } else {
                return {
                    status: err.status,
                    result: err.json()
                };
            }

        })
}


    fileUpload(endPoint: string, data, tokenRequired, finalCallBack) {
        var xhr;
        if (XMLHttpRequest) {
            xhr = new XMLHttpRequest();
        }

        var formData = new FormData();
        if (data != undefined && data != '') {
            for (var key in data) {
                formData.append(key, data[key]);
            }

        }
       
        xhr.upload.addEventListener("loadstart", function (e) {
        }, false);
        xhr.upload.addEventListener("load", function (e) {
        }, false);
        xhr.upload.addEventListener('error', function (e) {
        }, false);
        xhr.onreadystatechange = function (e) {
            setTimeout(() => {
                if (finalCallBack != undefined) finalCallBack(xhr.responseText);
            }, 2000);
        };
        xhr.open('post', CONFIG._url + endPoint, true);
        if (tokenRequired) xhr.setRequestHeader("Authorization", 'Bearer ' + LocalStorage.getValue('token'));
        xhr.upload.addEventListener("progress", function (e) {
            if (e.lengthComputable) {
                let percentage = Math.round((e.loaded * 100) / e.total);
                
                if(data!=undefined && data!=''){
                    for (var key in data) {
                        data[key].progress = percentage + "%";
                        // console.log( data[key].progress ,"percent");
                    }
                }
            }
        }, false);
        xhr.send(formData);
    }


    //DELETE METHOD//
    delete(endPoint: string, data: any = undefined, isJSON: boolean = true): any {
        let headers: Headers = new Headers();
        let requestoptions: RequestOptions;
        let theBody = {};
        if (data != undefined) {
            theBody = (isJSON) ? JSON.stringify(data) : data;
        }
        headers.append("Content-Type", 'application/json');
        headers.append("Authorization", 'Bearer ' + LocalStorage.getValue('token'));
        headers.append('Access-Control-Allow-Origin', '*');
        requestoptions = new RequestOptions({
            method: RequestMethod.Delete,
            url: CONFIG._url + endPoint,
            headers: headers,
            body: theBody
        })
        return this.http.request(new Request(requestoptions))
            .toPromise()
            .then((res) => {
                return {
                    status: res.status,
                    result: res.json()
                }
            }, (err) => {
                if (err.status == 401) {
                    this.logOut();
                } else {
                    return {
                        status: err.status,
                        result: err.json()
                    };
                }

            })
    }



    //EDIT METHOD//
    edit(endPoint: string, data: any, isJSON: boolean = true) {
        let headers: Headers = new Headers();
        let requestoptions: RequestOptions;
        let theBody: any;
        if (isJSON) {
            theBody = JSON.stringify(data);
        } else {
            theBody = data
        }
        headers.append("Content-Type", 'application/json');
        headers.append("Authorization", 'Bearer ' + LocalStorage.getValue('token'));
        headers.append('Access-Control-Allow-Origin', '*');
        requestoptions = new RequestOptions({
            method: RequestMethod.Post,
            url: CONFIG._url + endPoint,
            headers: headers,
            body: theBody
        })

        return this.http.request(new Request(requestoptions))
            .toPromise()
            .then((res) => {
                return {
                    status: res.status,
                    result: res.json()
                }
            }, (err) => {
                if (err.status == 401) {
                    this.logOut();
                } else {
                    return {
                        status: err.status,
                        result: err.json()
                    };
                }

            })
    }

    // HTTP ERROR HANDLE //
    handleError(error) {
        return Observable.throw(error.json().error || 'Server error');
    }

    //CLEAR DATA //
    logOut() {
        localStorage.removeItem('userData');
        localStorage.removeItem('user');
        // LocalStorage.createJWT();
        this.router.navigate(['']);
        //CONFIG.GET_APP.getRootNav().setRoot(SignIn);        
    }
    
}