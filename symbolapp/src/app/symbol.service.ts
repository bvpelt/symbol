import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Symbol } from './symbol';
import { SYMBOLS } from './mock-symbols';
import { MessageService } from './message.service';
import { Message, Severity } from './message';


@Injectable({
  providedIn: 'root'
})
export class SymbolService {

  private apiEndpoint = 'http://localhost:8080/symbols/';  // URL to web api
  private apiName = this.apiEndpoint + 'name/';
  private apiLookup = this.apiEndpoint + 'lookup/';
  private apiPrefix = this.apiEndpoint + 'prefix';

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getSymbols(name: string, limit?: string): Observable<Symbol[]> {

    var message: Message =  new Message(new Date(), Severity.info, 'Get symbol name: ' + name );

    this.messageService.add(message);
    if (typeof (limit) != undefined) {
      let params = new HttpParams();
      params = params.append("limit", limit!);
      const options = {
        params: params
      };
      return this.http.get<Symbol[]>(this.apiLookup + name, options);
    } else {
      return this.http.get<Symbol[]>(this.apiLookup + name);
    }
  }

  getSymbolName(name: string): Observable<Symbol> {
    return this.http.get<Symbol>(this.apiName + name);
  }

  getSymbolId(id: string): Observable<Symbol> {
    return this.http.get<Symbol>(this.apiEndpoint + id);
  }

  getPrefixes(): Observable<string[]> {
    return this.http.get<string[]>(this.apiPrefix);
  }
}
