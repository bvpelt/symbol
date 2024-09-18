import * as supportedBrowsers from '../supportedBrowsers';
import { detect } from 'detect-browser';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from './message.service';
import { Message, Severity } from './message';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Symbolen';
  browserSupported = '';
  message = '';

  constructor(private modalService: NgbModal,  private messageService: MessageService) {}

 ngOnInit(): void {
    this.browserSupported = supportedBrowsers.test(navigator.userAgent) ? '' : 'not';
    this.message = `Your current browser ${detect()?.name} version ${detect()?.version} is ${this.browserSupported} supported`;
    this.messageService.add(new Message(new Date(), Severity.info, 'AppComponent: ' + this.message));
  }

  public open(modal: any): void {
    this.modalService.open(modal);
  }
}
