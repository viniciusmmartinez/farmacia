import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { HttpClientService } from './app/arquitetura/shared/services/http-client.service';

if (environment.server) {
  enableProdMode();
}

const NOME_SISTEMA: string = 'FARMACIA';
HttpClientService.setNomeSistema(NOME_SISTEMA);

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
