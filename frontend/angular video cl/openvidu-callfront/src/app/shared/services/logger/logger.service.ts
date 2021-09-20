import { Injectable } from '@angular/core';
import { ILogService } from '../../types/logger-type';
import { environment } from '../../../../environments/environment';

@Injectable({
	providedIn: 'root'
})
export class LoggerService implements ILogService {
	public log;
	public LOG_FNS = [];
	public MSG_PREFIXES = [
		['[', ']'],
		['[', '] WARN: '],
		['[', '] ERROR: ']
	];

	constructor() {
	}

	private getLoggerFns(prefix: string) {
		this.log = window.console;
		this.LOG_FNS = [this.log.log, this.log.warn, this.log.error];
		const loggerFns = this.LOG_FNS.map((logTemplFn, i) => {
			return logTemplFn.bind(this.log, this.MSG_PREFIXES[i][0] + prefix + this.MSG_PREFIXES[i][1]);
		});
		return loggerFns;
	}

	public get(prefix: string) {
		const prodMode = environment.production;
		const loggerService = this;
		return {
			d: function(...args: any[]) {
				if (!prodMode) {
					loggerService.getLoggerFns(prefix)[0].apply(this.log, arguments);
				}
			},
			w: function(...args: any[]) {
				loggerService.getLoggerFns(prefix)[1].apply(this.log, arguments);

			},
			e: function(...args: any[]) {
				loggerService.getLoggerFns(prefix)[2].apply(this.log, arguments);
			}
		};
	}
}
