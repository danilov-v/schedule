import { call, put, takeEvery, all } from "redux-saga/effects";
import {
  FETCH_PERIODS,
  CREATE_PERIOD,
  EDIT_PERIOD,
  REMOVE_PERIOD,
  updatePeriods,
} from "redux/actions/periods";
import { periodRequestFail, closePeriodForm } from "redux/actions/forms";

import {
  getPeriods,
  createPeriod,
  updatePeriod,
  removePeriod as removePeriodAPI,
} from "helpers/api";
import { NotificationManager } from "helpers/notification-manager";
import {
  SUCCESS_PERIOD_NOTIFICATION_DATA,
  FAILED_PERIOD_NOTIFICATION_DATA,
} from "constants/notifications";

function* fetchPeriods(action) {
  try {
    const periods = yield call(getPeriods);
    yield put(updatePeriods(periods));
  } catch (e) {
    // TODO - remove session from store and update url
  }
}

function* createPeriods(action) {
  const { period } = action.payload;

  try {
    yield call(createPeriod, period);
    yield call(fetchPeriods);
    yield put(closePeriodForm());
    NotificationManager.fire(SUCCESS_PERIOD_NOTIFICATION_DATA);
  } catch (e) {
    yield put(periodRequestFail(e));
    NotificationManager.fire(FAILED_PERIOD_NOTIFICATION_DATA);

    //NOTIFY USER ABOUT PERIODS INTERSCEPTION
  }
}

function* editPeriod(action) {
  const { period } = action.payload;

  try {
    yield call(updatePeriod, period);
    yield call(fetchPeriods);
    yield put(closePeriodForm());
    NotificationManager.fire(SUCCESS_PERIOD_NOTIFICATION_DATA);
  } catch (e) {
    yield put(periodRequestFail(e));
    NotificationManager.fire(FAILED_PERIOD_NOTIFICATION_DATA);

    //NOTIFY USER ABOUT PERIODS INTERSCEPTION
  }
}

function* removePeriod(action) {
  const { periodId } = action.payload;

  try {
    yield call(removePeriodAPI, periodId);

    yield call(fetchPeriods);
  } catch (e) {}
}

function* fetchPeriodsSaga() {
  yield takeEvery(FETCH_PERIODS, fetchPeriods);
}

function* createPeriodSaga() {
  yield takeEvery(CREATE_PERIOD, createPeriods);
}

function* editPeriodSaga() {
  yield takeEvery(EDIT_PERIOD, editPeriod);
}

function* removePeriodSaga() {
  yield takeEvery(REMOVE_PERIOD, removePeriod);
}

export default function* watchCalendars() {
  yield all([
    fetchPeriodsSaga(),
    createPeriodSaga(),
    editPeriodSaga(),
    removePeriodSaga(),
  ]);
}
