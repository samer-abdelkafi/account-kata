import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HistoryComponent} from './history.component';
import {AccountService} from '../shared/service/account.service';
import {Operation} from '../shared/model/operation';
import {Account} from '../shared/model/account';

import {of} from 'rxjs';

describe('HistoryComponent', () => {
  let component: HistoryComponent;
  let fixture: ComponentFixture<HistoryComponent>;
  let accountServiceSpy: jasmine.SpyObj<AccountService>;

  const operations = [
    new Operation(1, -10, new Date(), 'DEBIT'),
    new Operation(1, 10, new Date(), 'CREDIT')
  ];

  const account = new Account(1, 'current account', 0);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HistoryComponent],
      providers: [
        {
          provide: AccountService,
          useValue: jasmine.createSpyObj('AccountService',
            ['getOperation', 'getAccount'])
        }
      ]
    })
      .compileComponents();

    accountServiceSpy = TestBed.inject(AccountService) as jasmine.SpyObj<AccountService>;
    accountServiceSpy.getOperation.and.returnValue(of(operations));
    accountServiceSpy.getAccount.and.returnValue(of(account));
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should display operation history', () => {
    expect(component.operations).toEqual(operations);
    expect(component.account).toEqual(account);
  });
});
