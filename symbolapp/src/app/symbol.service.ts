import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Symbol } from './symbol';
import { SYMBOLS } from './mock-symbols';

@Injectable({
  providedIn: 'root'
})
export class SymbolService {

  constructor() { }

  getSymbols(): Observable<Symbol []> {
    const symbols = of(SYMBOLS);
    return symbols;
  }
}
