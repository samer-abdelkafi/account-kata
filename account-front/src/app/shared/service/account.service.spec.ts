import { TestBed } from '@angular/core/testing';

import { AccountService } from './account.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Operation} from '../model/operation';

describe('AccountService', () => {


  let service: AccountService;

  const operations  = [
    new Operation(1, -10, new Date(), 'DEBIT'),
    new Operation(1, 10, new Date(), 'CREDIT')
  ];

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(AccountService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be created', () => {

    service.getOperation(1).subscribe(operations => {
      expect(operations.length).toBe(2);
      expect(operations).toEqual(operations);
    });

    const req = httpMock.expectOne('/api/accounts/1');
    expect(req.request.method).toBe("GET");
    req.flush(operations);

  });





});
