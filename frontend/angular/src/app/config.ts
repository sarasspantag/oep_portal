export class CONFIG {      
    
    public static _url: string = "http://localhost:8080/";
    public static _imageurl: string = "http://localhost:8080/onlineexamine/";

    //public static _url: string = "http://139.59.62.244:8080/";
    //public static _imageurl: string = "http://139.59.62.244:8080/onlineexamine/";  

    public static _loggedIn: boolean=true;
    public static _user_mobile: any='';
    public static _active_url:any='';
    public static forgot_cont:boolean = false; 
    public static forgot_user:any;
    public static forgot_mobile_no:any;
    public static showmenu: boolean=true;
    constructor() { }
}
