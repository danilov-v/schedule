export const MANAGE_UNITS = "MANAGE_UNITS";
export const MANAGE_EVENT_TYPES = "MANAGE_EVENT_TYPES";
export const MANAGE_PERIODS = "MANAGE_PERIODS";
export const MANAGE_EVENTS = "MANAGE_EVENTS";

export const ROLES_PERMISSIONS = {
  superuser: [MANAGE_UNITS, MANAGE_EVENT_TYPES, MANAGE_PERIODS, MANAGE_EVENTS],
  admin: [MANAGE_UNITS, MANAGE_EVENT_TYPES, MANAGE_PERIODS, MANAGE_EVENTS],
  user: [],
};
