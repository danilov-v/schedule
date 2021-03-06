import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { useSelector, useDispatch } from "react-redux";
import { getAuthData } from "redux/selectors/auth";
import { format } from "date-fns";
import { getFormattedPeriodsSelector } from "redux/selectors/periods";
import {
  createPeriod,
  updatePeriod,
  removePeriod,
  fetchPeriods,
} from "redux/actions/periods";
import { PERIOD_FORM } from "constants/forms";
import { openForm, closeForm } from "redux/actions/forms";
import { Button, Container } from "reactstrap";
import { Title } from "components/title/title";
import { PERIOD_CONFIRMATION_OPTIONS } from "constants/confirmations";
import { useConfirmation } from "components/confirmation-service/confirmation-service";
import { PeriodsList } from "./periods-list";
import { PeriodsPopup } from "./periods-popup";
import { MANAGE_PERIODS } from "constants/permissions";
import { checkPermission } from "utils/permissions";

export function Periods() {
  const dispatch = useDispatch();
  const confirm = useConfirmation();
  const periods = useSelector(getFormattedPeriodsSelector);
  const { role } = useSelector(getAuthData);

  useEffect(() => {
    dispatch(fetchPeriods());
  }, [dispatch]);

  const onPeriodSubmit = (periodData, isEdit) => {
    const data = {
      ...periodData,
      startDate: format(periodData.startDate, "yyyy-MM-dd"),
      endDate: format(periodData.endDate, "yyyy-MM-dd"),
    };

    isEdit ? dispatch(updatePeriod(data)) : dispatch(createPeriod(data));
  };

  const tryToRemove = periodId => {
    confirm(PERIOD_CONFIRMATION_OPTIONS).then(() => {
      dispatch(removePeriod(periodId));
    });
  };

  const openNewForm = () => dispatch(openForm({ formName: PERIOD_FORM }));

  const openEditForm = formData =>
    dispatch(openForm({ formName: PERIOD_FORM, isEdit: true, formData }));

  const onCloseForm = () => dispatch(closeForm());

  return (
    <Container>
      <Title text="Периоды" />
      <PeriodsList
        onPeriodEdit={openEditForm}
        onPeriodRemove={tryToRemove}
        periods={periods}
        userRole={role}
      />
      <Button
        outline
        color="primary"
        size="lg"
        className="font-weight-bold float-right"
        onClick={openNewForm}
        hidden={!checkPermission(role, MANAGE_PERIODS)}
      >
        +
      </Button>
      <PeriodsPopup toggle={onCloseForm} onPeriodSubmit={onPeriodSubmit} />
    </Container>
  );
}

Periods.propTypes = {
  periods: PropTypes.arrayOf(PropTypes.shape({})),
};

Periods.defaultProps = {
  periods: [],
};
