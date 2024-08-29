import {Component, Input} from '@angular/core';
import { Symbol } from '../symbol';

@Component({
  selector: 'app-symbol-detail',
  templateUrl: './symbol-detail.component.html',
  styleUrls: ['./symbol-detail.component.css']
})
export class SymbolDetailComponent {
  @Input() symbol?: Symbol;
 
}
