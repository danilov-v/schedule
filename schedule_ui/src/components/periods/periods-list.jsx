import React from "react";
import PropTypes from "prop-types";
import { format } from "date-fns";
import { Table, Button } from "reactstrap";
import { MANAGE_PERIODS } from "constants/permissions";
import { checkPermission } from "utils/permissions";

export function PeriodsList({
  periods,
  onPeriodEdit,
  onPeriodRemove,
  userRole,
}) {
  const isManageAble = checkPermission(userRole, MANAGE_PERIODS);

  const renderRow = (period, index) => (
    <tr key={period.periodId}>
      <th scope="row">{index}</th>
      <td>{period.name}</td>
      <td>{format(period.startDate, "dd/MM/yyyy")}</td>
      <td>{format(period.endDate, "dd/MM/yyyy")}</td>
      <td hidden={!isManageAble}>
        <Button onClick={() => onPeriodEdit(period)} color="warning">
          Изменить
        </Button>
        <Button close onClick={() => onPeriodRemove(period.periodId)} />
      </td>
    </tr>
  );
  return (
    <div className="event-type-list">
      <Table hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Название</th>
            <th>Начало</th>
            <th>Конец</th>
            <th hidden={!isManageAble}></th>
          </tr>
        </thead>
        <tbody>{periods.map(renderRow)}</tbody>
      </Table>
    </div>
  );
}

PeriodsList.propTypes = {
  periods: PropTypes.arrayOf(
    PropTypes.shape({
      periodId: PropTypes.number,
      name: PropTypes.string,
      startDate: PropTypes.date,
      endDate: PropTypes.date,
    })
  ),
  onPeriodEdit: PropTypes.func.isRequired,
  onPeriodRemove: PropTypes.func.isRequired,
  userRole: PropTypes.string,
};

PeriodsList.defaultProps = {
  periods: [],
};
