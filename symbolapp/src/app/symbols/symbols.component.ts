import { Component, OnInit } from '@angular/core';
import { Symbol } from '../symbol';
import { SYMBOLS } from '../mock-symbols';
import { SymbolService } from '../symbol.service';

@Component({
  selector: 'app-symbols',
  templateUrl: './symbols.component.html',
  styleUrls: ['./symbols.component.css'],
})
export class SymbolsComponent implements OnInit {
  symbols: Symbol[] = [];
  prefixes: string[] = [];
  selectedSymbol?: Symbol;
  searchsymbol?: string;

  constructor(private symbolService: SymbolService) {

  }

  ngOnInit() {
    //    this.getSymbols('lt');
  }

  onSearch(search?: string): void {
    this.prefixes = [];
    if ((typeof (search) == "undefined")) {
      search = 'lt001';
    }
    this.getSymbols(search, '40');
  }

  onHelp(): void {
    this.symbols = [];
    this.getPrefixes();
  }

  onSelect(symbol: Symbol): void {
    console.log("Draw symbol: " + symbol.name);
    this.selectedSymbol = symbol;
  }

  getSymbols(name: string, limit?: string): void {
    this.symbolService.getSymbols(name, limit)
      .subscribe(symbols => this.symbols = symbols);
  }

  getPrefixes(): void {
    this.symbolService.getPrefixes()
      .subscribe(prefixes => this.prefixes = prefixes)
  }

}
