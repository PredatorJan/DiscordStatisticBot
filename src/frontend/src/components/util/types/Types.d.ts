export type User = {
  id: number,
  discordid: string,
  name: string,
  role: Role
};

export type Role = {
  id: number,
  name: string,
  description: string
}

export type Message = {
  id: number,
  timestamp: string,
  user: User,
  guild: Guild
}

export type Guild = {
  id: number,
  discordId: string,
  name: string,
  currentMembers: number,
  activeMembers: number,
  inactiveMembers: number
}

export type UserInfo = {
  guildId: number,
  userId: number,
  rateDay: number,
  rateWeek: number,
  rateMonth: number
}