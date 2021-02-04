import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OperationComponent} from './operation.component';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';
import {AccountService} from '../shared/service/account.service';
import {of} from 'rxjs';

describe('OperationComponent', () => {

  let component: OperationComponent;
  let fixture: ComponentFixture<OperationComponent>;
  const formBuilder: FormBuilder = new FormBuilder();
  let accountServiceSpy: jasmine.SpyObj<AccountService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        OperationComponent
      ],
      imports: [
        ReactiveFormsModule
      ],
      providers: [
        {
          provide: FormBuilder,
          useValue: formBuilder
        },
        {
          provide: AccountService,
          useValue: jasmine.createSpyObj('AccountService',
            ['deposit', 'withdrawal', 'getOperation'])
        }]
    }).compileComponents();

    accountServiceSpy = TestBed.inject(AccountService) as jasmine.SpyObj<AccountService>;

  });


  beforeEach(() => {
    fixture = TestBed.createComponent(OperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should deposit', () => {
    accountServiceSpy.deposit.and.returnValue(of());
    component.deposit();
    expect(accountServiceSpy.deposit.calls.count())
      .toBe(1, 'deposit called once');
  })


  it('should withdrawal', () => {
    accountServiceSpy.withdrawal.and.returnValue(of());
    component.withdrawal();
    expect(accountServiceSpy.withdrawal.calls.count())
      .toBe(1, 'withdrawal called once');
  })

});
