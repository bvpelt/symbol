import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Symbol } from './symbol';
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

    //var message: Message = new Message(new Date(), Severity.info, 'getSymbols name: ' + name);
    //this.messageService.add(message);

    if (typeof (limit) != undefined) {
      let params = new HttpParams();
      params = params.append("limit", limit!);
      const options = {
        params: params
      };
      return this.http.get<Symbol[]>(this.apiLookup + name, options)
        .pipe(
          tap(_ => {
            var message: Message = new Message(new Date(), Severity.info, 'getSymbols name: ' + name);
            this.messageService.add(message);
          }),
          catchError(this.handleError<Symbol[]>('getSymbols', []))
        );
    } else {
      return this.http.get<Symbol[]>(this.apiLookup + name)
        .pipe(
          tap(_ => {
            var message: Message = new Message(new Date(), Severity.info, 'getSymbols name: ' + name);
            this.messageService.add(message);
          }),
          catchError(this.handleError<Symbol[]>('getSymbols', []))
        );
    }
  }

  getSymbolName(name: string): Observable<Symbol> {
    return this.http.get<Symbol>(this.apiName + name)
      .pipe(
        tap(_ => {
          var message: Message = new Message(new Date(), Severity.info, 'getSymbolName: ' + name);
          this.messageService.add(message);
        }),
        catchError(this.handleError<Symbol>('getSymbolName'))
      );
  }

  getSymbolId(id: string): Observable<Symbol> {

    return this.http.get<Symbol>(this.apiEndpoint + id)
      .pipe(
        tap(_ => {
          var message: Message = new Message(new Date(), Severity.info, 'getSymbolId ' + id);
          this.messageService.add(message);
        }),

        catchError(this.handleError<Symbol>('getSymbolId'))
      );
  }

  getPrefixes(): Observable<string[]> {
    return this.http.get<string[]>(this.apiPrefix)
      .pipe(
        tap(_ => {
          var message: Message = new Message(new Date(), Severity.debug, 'getPrefixes');
          this.messageService.add(message);
        }),
        catchError(this.handleError<string[]>('getPrefixes', []))
      );
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      var message: Message = new Message(new Date(), Severity.error, operation + ' failed: ' + error.message);
      this.messageService.add(message);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
