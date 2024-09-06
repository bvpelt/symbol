import { Component } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent {
  isAll: boolean = true;
  isTrace: boolean = true;
  isDebug: boolean = true;
  isInfo: boolean = true;
  isWarning: boolean = true;
  isError: boolean = true;
  isFatal: boolean = true;

  constructor(public messageService: MessageService) {}

  filterChange(selected: any) {
    console.log('selected name: ' + selected.target.name + ' value: ' + selected.target.value + ' checked: ' + selected.target.checked);
    if (selected.target.name === 'isAll') {
      if (selected.target.checked == true) {
        this.isTrace = true;
        this.isDebug = true;
        this.isInfo = true;
        this.isWarning = true;
        this.isError = true;
        this.isFatal = true;
      }
    } else {
      this.isAll = false;
    }
  }
}
