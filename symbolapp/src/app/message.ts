export enum Severity {
  trace   = "TRACE  ",
  debug   = "DEBUG  ",
  info    = "INFO   ",
  warning = "WARNING",
  error   = "ERROR  ",
  fatal   = "FATAL  "
};

export class Message {
  timestamp: Date;
  severity: Severity;
  message: string;

  constructor(timestamp: Date, severify: Severity, message: string) {
    this.timestamp = timestamp;
    this.severity = severify;
    this.message = message;
  }
}
