import { Component, OnInit, HostListener } from '@angular/core';
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
  selectedIndex: number = 0;

  constructor(private symbolService: SymbolService) {
  }

  ngOnInit() {
  }

  onSearch(search?: string): void {
    this.prefixes = [];
    console.log('search argument: ', search);
    if ((typeof (search) === "undefined")) {
      search = 'lt001';
    }
    this.getSymbols(search, '40');
    document.querySelector('li')?.focus();   
  }

  onHelp(): void {
    this.symbols = [];
    this.getPrefixes();
  }

  onSelect(symbol: Symbol, index: number): void {
    // console.log("Draw symbol: " + symbol.name);
    this.selectedIndex = index;
    this.selectedSymbol = symbol;
    //    console.log('onselect - index: ', this.selectedIndex, ' symbol:', symbol);
  }

  @HostListener('document:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    //  console.log('selectedindex: ' + this.selectedIndex + ' ' + event);
    switch (event.key) {
      case 'ArrowUp':
        this.selectedIndex = Math.max(0, this.selectedIndex - 1);
        this.scrollToSelectedItem();
        break;
      case 'ArrowDown':
        this.selectedIndex = Math.min(this.symbols.length - 1, this.selectedIndex + 1);
        this.scrollToSelectedItem();
        break;
      case 'Enter':
        // Handle item selection here
        break;
    }
  }

  scrollToSelectedItem() {
    const list = document.querySelector('ul');
    const selectedItem = list?.querySelector('li:nth-child(' + (this.selectedIndex + 1) + ')');
    //    console.log('list: ', list, ' selectedItem: ', selectedItem);
    if (selectedItem) {
      this.selectedSymbol = this.symbols[this.selectedIndex];
      selectedItem.scrollIntoView({ behavior: 'smooth' });
    }
  }

  getSymbols(name: string, limit?: string): void {
    this.symbolService.getSymbols(name, limit)
      .subscribe(symbols => { 
        this.symbols = symbols; 
        if (this.symbols.length > 0) {
          this.selectedSymbol = this.symbols[this.selectedIndex];
        }
      });
  }

  getPrefixes(): void {
    this.symbolService.getPrefixes()
      .subscribe(prefixes => this.prefixes = prefixes)
  }

}
