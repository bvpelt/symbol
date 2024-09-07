import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
    if (typeof (limit) != undefined) {
      let params = new HttpParams();
      params = params.append("limit", limit!);
      const options = {
        params: params,
        headers: new HttpHeaders({ 'Accept': 'application/json' })
      };

      return this.http.get<Symbol[]>(this.apiLookup + name, options)
        .pipe(
          tap(_ => {
            this.messageService.add(new Message(new Date(), Severity.info, 'getSymbols name: ' + name));
          }),
          catchError(this.handleError<Symbol[]>('getSymbols', []))
        );
    } else {
      return this.http.get<Symbol[]>(this.apiLookup + name)
        .pipe(
          tap(_ => {
            this.messageService.add(new Message(new Date(), Severity.info, 'getSymbols name: ' + name));
          }),
          catchError(this.handleError<Symbol[]>('getSymbols', []))
        );
    }
  }

  getSymbolName(name: string): Observable<Symbol> {
    return this.http.get<Symbol>(this.apiName + name)
      .pipe(
        tap(_ => {
          this.messageService.add(new Message(new Date(), Severity.info, 'getSymbolName: ' + name));
        }),
        catchError(this.handleError<Symbol>('getSymbolName'))
      );
  }

  getSymbolId(id: string): Observable<Symbol> {

    return this.http.get<Symbol>(this.apiEndpoint + id)
      .pipe(
        tap(_ => {
          this.messageService.add(new Message(new Date(), Severity.info, 'getSymbolId ' + id));
        }),

        catchError(this.handleError<Symbol>('getSymbolId'))
      );
  }

  getPrefixes(): Observable<string[]> {
    return this.http.get<string[]>(this.apiPrefix)
      .pipe(
        tap(_ => {
          this.messageService.add(new Message(new Date(), Severity.debug, 'getPrefixes'));
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

      this.messageService.add(new Message(new Date(), Severity.error, operation + ' failed: ' + error.message));

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
