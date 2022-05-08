import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {User, UserService, Profile} from '../core';
import {concatMap, tap} from 'rxjs/operators';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private fb: FormBuilder) {
    {
      this.authForm = this.fb.group({
        'firstName': [''],
        'lastName': [''],
        'email': ['']
      });
    }
  }

  profile: Profile;
  currentUser: User;
  isUser: boolean;
  authForm: any;

  ngOnInit() {

    this.route.data.pipe(
      concatMap((data: { profile: Profile }) => {
        this.profile = data.profile;
        // Load the current user's data.
        return this.userService.currentUser.pipe(tap(
          (userData: User) => {
            this.currentUser = userData;
            this.isUser = (this.currentUser.userName === this.route.snapshot.params.userName);
          }
        ));
      })
    ).subscribe();

    this.authForm = this.fb.group({
      'firstName': [this.currentUser.firstName],
      'lastName': [this.currentUser.lastName],
      'email': [this.currentUser.email]
    });
  }

}
