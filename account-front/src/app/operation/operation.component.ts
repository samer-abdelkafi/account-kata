import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {AccountService} from '../shared/service/account.service';

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.css']
})
export class OperationComponent implements OnInit {

  private accountId = 1;

  done = false;

  operationForm = this.fb.group({
    amount: [''],
  });

  constructor(private fb: FormBuilder, private accountService: AccountService) {
  }

  ngOnInit(): void {

  }

  deposit(): void {
    this.done = false;
    this.accountService.deposit(this.accountId, this.operationForm.get(['amount'])?.value)
      .subscribe(() => {
        this.done = true;
        this.operationForm.reset();
      });

  }

  withdrawal(): void {
    this.done = false;
    this.accountService.withdrawal(this.accountId, this.operationForm.get(['amount'])?.value)
      .subscribe(() => {
        this.done = true;
        this.operationForm.reset();
      });
  }

}
