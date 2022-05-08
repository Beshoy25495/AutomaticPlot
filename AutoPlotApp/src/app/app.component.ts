import { Component, OnInit } from '@angular/core';

import {ApiService, UserService} from './core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {
  constructor(private userService: UserService, private apiService: ApiService) {}

  ngOnInit() {
    this.userService.populate();
  }
}
