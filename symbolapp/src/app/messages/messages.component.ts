import { Component } from '@angular/core';
import { MessageService } from '../message.service';
import { Message, Severity } from '../message';
import { Filter } from '../filter';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent {
  filter: Filter = new Filter();

  constructor(public messageService: MessageService) { }

  filterChange(selected: any) {
    console.log('selected name: ' + selected.target.name + ' value: ' + selected.target.value + ' checked: ' + selected.target.checked);
    if (selected.target.name === 'isAll') {
      if (selected.target.checked == true) {
        this.filter.isTrace = true;
        this.filter.isDebug = true;
        this.filter.isInfo = true;
        this.filter.isWarning = true;
        this.filter.isError = true;
        this.filter.isFatal = true;
      }
    } else {
      this.filter.isAll = false;
    }
  }

  findMessage(message: Message, filter: Filter): boolean {
    var filtered: boolean = false; // filtered means include in filtered array list of the pipe
    if (filter.isAll) {
      filtered = true;
    } else {
      if (!filtered) {
        if (!filtered && filter.isTrace && message.severity === Severity.trace) {
          filtered = true;
        }
        if (!filtered && filter.isDebug && message.severity === Severity.debug) {
          filtered = true;
        }
        if (!filtered && filter.isInfo && message.severity === Severity.info) {
          filtered = true;
        }
        if (!filtered && filter.isWarning && message.severity === Severity.warning) {
          filtered = true;
        }
        if (!filtered && filter.isError && message.severity === Severity.error) {
          filtered = true;
        }
        if (!filtered && filter.isFatal && message.severity === Severity.fatal) {
          filtered = true;
        }
      }
    }
    //console.log('findMessage message: ', message, ' filtered: ', filtered);
    return filtered;
  }
}
