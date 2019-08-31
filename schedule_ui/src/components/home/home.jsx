import React, { useState } from "react";
import { Switch, Route } from "react-router-dom";
import { addMonths } from "date-fns";
import { isEmpty, cloneDeep } from "lodash";
import { useUnitsTree, useEventTypes } from "helpers/hooks/apiEffects";
import { Container } from "reactstrap";
import { Timeline } from "components/timeline/timeline";
import { NavBar } from "components/header/header";
import { ConfirmationServiceProvider } from "components/confirmation-service/confirmation-service";
import { EventTypes } from "components/event-types/event-types";
import { getDayWithoutMinutes } from "utils/date";

import "./home.scss";

function getUnitsFromUnitsTree(root) {
  if (!root.length) return;

  const stack = cloneDeep(root).reverse();
  const array = [];

  while (stack.length !== 0) {
    const node = stack.pop();
    if (!isEmpty(node.childUnit)) {
      node.childUnit.forEach(unit => {
        stack.push(unit);
      });
    }
    array.push({
      parentId: node.parentId,
      title: node.title,
      unitId: node.unitId,
    });
  }

  return array;
}

export function Home() {
  const now = getDayWithoutMinutes(new Date());

  const [startDate, setStartDate] = useState(now);
  const [endDate, setEndDate] = useState(addMonths(now, 2));
  const [eventTypes, fetchEventTypes] = useEventTypes();
  const [unitsTree, fetchUnitsTree] = useUnitsTree(startDate);

  const onUnitsUpdate = () => {
    fetchUnitsTree();
  };

  const onEventTypesUpdate = () => {
    fetchUnitsTree(); //if event deleted then all relative events will also destroed
    fetchEventTypes();
  };

  return (
    <ConfirmationServiceProvider>
      <Container fluid>
        <NavBar
          startDate={startDate}
          endDate={endDate}
          onChangeStartDate={setStartDate}
          onChangeEndDate={setEndDate}
        />
        <Switch>
          <Route
            exact
            path="/"
            render={() => (
              <Timeline
                startDate={startDate}
                endDate={endDate}
                eventTypes={eventTypes}
                unitsTree={unitsTree}
                units={getUnitsFromUnitsTree(unitsTree)}
                onUnitsUpdate={onUnitsUpdate}
              />
            )}
          />
          <Route
            path="/event_types"
            render={() => (
              <EventTypes
                eventTypes={eventTypes}
                onEventTypesUpdate={onEventTypesUpdate}
              />
            )}
          />
        </Switch>
      </Container>
    </ConfirmationServiceProvider>
  );
}
