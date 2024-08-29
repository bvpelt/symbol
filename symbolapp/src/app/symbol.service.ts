import { Injectable } from '@angular/core';
import { Symbol } from './symbol';
import { SYMBOLS } from './mock-symbols';

@Injectable({
  providedIn: 'root'
})
export class SymbolService {

  constructor() { }

  getSymbols(): Symbol [] {
    return SYMBOLS;
  }
}
