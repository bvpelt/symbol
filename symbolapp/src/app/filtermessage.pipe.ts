import { Pipe, PipeTransform } from '@angular/core';
import { Filter } from './filter';
import { Message } from './message';

/* See https://danielk.tech/home/angular-how-to-apply-filters-to-ngfor */

@Pipe({
  name: 'filtermessage'
})
export class FiltermessagePipe implements PipeTransform {
 
  /*
  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }
    */

  transform(messages: Message[], myFilter: Filter, callback: (item: any, filter: Filter) => boolean): Message[] {
    console.log('transform messages: ', messages, ' filter: ', myFilter);
    if (!messages || !callback) {
      return messages;
    }
    return messages.filter(item => callback(item, myFilter));
  }

}
