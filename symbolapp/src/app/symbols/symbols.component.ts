import { Component } from '@angular/core';
import { Symbol } from '../symbol';

@Component({
  selector: 'app-symbols',
  templateUrl: './symbols.component.html',
  styleUrls: ['./symbols.component.css']
})
export class SymbolsComponent {
  symbol: Symbol = {
    id: 2321,
    type: "Punt",
    size: 0,
    rotation: 0,
    fill: "#ffc8be",
    fillopacity: 1,
    stroke: "#999999",
    strokeopacity: 0,
    strokewidth: 1,
    name: "px005"
  };
}
