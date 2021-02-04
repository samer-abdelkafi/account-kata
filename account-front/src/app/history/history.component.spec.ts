import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HistoryComponent} from './history.component';
import {AccountService} from '../shared/service/account.service';
import {Operation} from '../shared/model/operation';
import {of} from 'rxjs';

describe('HistoryComponent', () => {
  let component: HistoryComponent;
  let fixture: ComponentFixture<HistoryComponent>;
  let accountServiceSpy: jasmine.SpyObj<AccountService>;

  const operations  = [
    new Operation(1, -10, new Date(), 'DEBIT'),
    new Operation(1, 10, new Date(), 'CREDIT')
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HistoryComponent],
      providers: [
        {
          provide: AccountService,
          useValue: jasmine.createSpyObj('AccountService',
            ['getOperation'])
        }
      ]
    })
      .compileComponents();

    accountServiceSpy = TestBed.inject(AccountService) as jasmine.SpyObj<AccountService>;
    accountServiceSpy.getOperation.and.returnValue(of(operations));
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
  });
});
