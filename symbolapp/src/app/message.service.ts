import { Injectable } from '@angular/core';
import { Message } from './message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  messages: Message[] = [];

  constructor() { }

  add(message: Message) {
    this.messages.push(message);
    //this.messages.sort(function (a,b):number { return new Date(a.timestamp) < new Date(b.timestamp) ? 1 : -1 });
  }

  clear() {
    this.messages = [];
  }
}
