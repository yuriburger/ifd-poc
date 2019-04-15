interface NavAttributes {
  [propName: string]: any;
}
interface NavWrapper {
  attributes: NavAttributes;
  element: string;
}
interface NavBadge {
  text: string;
  variant: string;
}
interface NavLabel {
  class?: string;
  variant: string;
}

export interface NavData {
  name?: string;
  url?: string;
  icon?: string;
  badge?: NavBadge;
  title?: boolean;
  children?: NavData[];
  variant?: string;
  attributes?: NavAttributes;
  divider?: boolean;
  class?: string;
  label?: NavLabel;
  wrapper?: NavWrapper;
}

export const navItems: NavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer'
  },
  {
    title: true,
    name: 'Flows'
  },
  {
    name: 'Fokker',
    url: '/fokker',
    icon: 'icon-user'
  },
  {
    name: 'Buitendienst Orders',
    url: '/buitendienst',
    icon: 'icon-briefcase'
  },
  {
    name: 'RvB Werkbakje',
    url: '/werkbakje',
    icon: 'icon-check'
  },
  {
    name: 'VHL Opdrachten',
    url: '/lab',
    icon: 'icon-pencil'
  }
];
