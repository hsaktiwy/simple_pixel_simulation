package main.java.miniWorld;
import java.util.List;
import java.util.ArrayList;
public class Rules {
    public static List<ruleBase> rules = new ArrayList<>();
    static class ruleBase
    {
        public float gravity;
        public float min_d;
        public float max_d;
        public int maxHeight;
        public int maxWidth;
        public Group group1;
        public Group group2;
        ruleBase(Group group1, Group group2, float gravity, float min_d, float max_d, int maxHeight, int maxWidth)
        {
            this.group1 = group1;
            this.group2 = group2;
            this.gravity = gravity;
            this.min_d = min_d;
            this.max_d = max_d;
            this.maxHeight = maxHeight;
            this.maxWidth = maxWidth;
        }
    }

    public static void addRule(Group group1, Group group2, float gravity, float min_d, float max_d, int maxHeight, int maxWidth)
    {
        ruleBase rule = new ruleBase(group1, group2, gravity, min_d, max_d, maxHeight, maxWidth);
        if (rules.contains(rule))
            return;
        rules.add(rule);
    }

    public static void clearRules()
    {
        rules.clear();
    }

    public static void applayAll()
    {
        for (ruleBase rule: rules)
        {
            applay(rule.group1, rule.group2, rule.gravity, rule.min_d, rule.max_d, rule.maxHeight, rule.maxWidth);
        }
    }

    static void applay(Group group1, Group group2,float gravity, float min_d, float max_d, int maxHeight, int maxWidth)
    {
        for (Partical p1: group1.list)
        {
            float fx = 0;
            float fy = 0;
            if (p1.pos.x < 0 || p1.pos.x >= maxWidth || p1.pos.y < 0 || p1.pos.y >= maxHeight)
            {
                p1.pos.x += p1.pos.vx;
                p1.pos.y += p1.pos.vy;
                continue;
            }
            for (Partical p2: group2.list)
            {
                double dx = p1.pos.x - p2.pos.x;
                double dy = p1.pos.y - p2.pos.y;
                double d = Math.sqrt((double)(dx*dx + dy*dy));
                if (d > min_d && d < max_d)
                {
                    double F = gravity * 1/d;
                    fx += (F*dx);
                    fy += (F*dy);
                }
            }
            p1.pos.vx = (p1.pos.vx + fx) * 0.5f;
            p1.pos.vy = (p1.pos.vy + fy) * 0.5f;
            p1.pos.x += p1.pos.vx;
            p1.pos.y += p1.pos.vy;
            if (p1.pos.x < 0 || p1.pos.x >= maxWidth)
                p1.pos.vx *=-1;
            if (p1.pos.y < 0 || p1.pos.y >= maxHeight)
                p1.pos.vy *=-1;
        }
    }
}
