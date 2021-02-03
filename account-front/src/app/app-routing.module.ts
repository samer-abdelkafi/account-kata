import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HistoryComponent} from './history/history.component';
import {OperationComponent} from './operation/operation.component';


const routes: Routes = [
  { path: '', redirectTo: 'history', pathMatch: 'full'  },
  { path: 'operation', component: OperationComponent },
  { path: 'history', component: HistoryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
