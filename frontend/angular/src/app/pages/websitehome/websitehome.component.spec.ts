import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebsitehomeComponent } from './websitehome.component';

describe('WebsitehomeComponent', () => {
  let component: WebsitehomeComponent;
  let fixture: ComponentFixture<WebsitehomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebsitehomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebsitehomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
